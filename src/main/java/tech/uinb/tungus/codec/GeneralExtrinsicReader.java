package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UInt64Reader;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import io.emeraldpay.polkaj.scale.reader.UnsupportedReader;
import io.emeraldpay.polkaj.scaletypes.EraReader;
import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;
import io.emeraldpay.polkaj.types.Hash512;

import java.util.Arrays;
import java.util.Optional;

public class GeneralExtrinsicReader<CALL extends ExtrinsicCall> implements ScaleReader<Extrinsic<CALL>> {
    private final static UInt64Reader UINT64 = new UInt64Reader();
    private Metadata metadata;
    private ReaderRegistry readerRegistry;

    private final TransactionInfoReader transactionInfoReader;

    public GeneralExtrinsicReader(Metadata metadata, ReaderRegistry readerRegistry, SS58Type.Network network) {
        this.metadata = metadata;
        this.readerRegistry = readerRegistry;
        transactionInfoReader = new TransactionInfoReader(network);
    }

    @Override
    public Extrinsic<CALL> read(ScaleCodecReader rdr) {
        byte[] internal = rdr.readByteArray();
        rdr = new ScaleCodecReader(internal);
        int type = rdr.readByte();
        boolean signed = (Extrinsic.TYPE_BIT_SIGNED & type) > 0;
        int version = Extrinsic.TYPE_UNMASK_VERSION & type;
        if (version != 4) {
            throw new IllegalStateException("Trying to read unsupported version: " + version);
        }
        Extrinsic<CALL> result = new Extrinsic<>();
        if (signed) {
            result.setTx(rdr.read(transactionInfoReader));
        }
        int moduleIndex = rdr.readUByte();
        int callIndex = rdr.readUByte();
        var name = getCallFullName(moduleIndex, callIndex);
        if (name.isEmpty()) {
            throw new IllegalStateException("metadata not contains Extrinsic(" + moduleIndex + "," + callIndex + ")");
        }
        var reader = readerRegistry.getReader(name.get());
        CALL call = (CALL) reader.read(rdr);
        call.setModuleIndex(moduleIndex);
        call.setCallIndex(callIndex);
        result.setCall(call);
        return result;
    }

    private Optional<String> getCallFullName(int moduleIndex, int callIndex) {
        var modules = metadata.getModules();
        if (modules == null || modules.isEmpty()) {
            return Optional.empty();
        }

        for (Metadata.Module module : modules) {
            if (module.getIndex() == moduleIndex) {
                var calls = module.getCalls();
                if (calls == null || calls.size() <= callIndex) {
                    return Optional.empty();
                }

                return Optional.of(module.getName() + "." + calls.get(callIndex).getName());
            }
        }

        return Optional.empty();
    }

    static class TransactionInfoReader implements ScaleReader<Extrinsic.TransactionInfo> {

        private static final UnionReader<Extrinsic.SR25519Signature> SIGNATURE_READER = new UnionReader<>(
                Arrays.asList(
                        new UnsupportedReader<>("ED25519 signatures are not supported"),
                        new SR25519SignatureReader(),
                        new UnsupportedReader<>("ECDSA signatures are not supported")
                )
        );
        private static final EraReader ERA_READER = new EraReader();

        private final SS58Type.Network network;

        public TransactionInfoReader(SS58Type.Network network) {
            this.network = network;
        }

        @Override
        public Extrinsic.TransactionInfo read(ScaleCodecReader rdr) {
            Extrinsic.TransactionInfo result = new Extrinsic.TransactionInfo();
            //Sender 的类型为<T::Lookup as StaticLookup>::Source
            result.setSender(new StaticLookupSourceReader(network).read(rdr));
            result.setSignature(rdr.read(SIGNATURE_READER).getValue());
            result.setEra(rdr.read(ERA_READER));
            result.setNonce(rdr.read(ScaleCodecReader.COMPACT_BIGINT).longValueExact());
            result.setTip(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
            return result;
        }
    }

    static class SR25519SignatureReader implements ScaleReader<Extrinsic.SR25519Signature> {

        @Override
        public Extrinsic.SR25519Signature read(ScaleCodecReader rdr) {
            return new Extrinsic.SR25519Signature(new Hash512(rdr.readByteArray(64)));
        }
    }
}
