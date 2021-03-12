package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.WriterRegistry;

public class FusotaoEventWriterRegistry implements WriterRegistry {
	private HashMap<Class, ScaleWriter> map = new HashMap<>();
	public FusotaoEventWriterRegistry() {
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenRevokeSubmitted.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsTokenRevokeSubmittedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenTransfered.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoTokenTokenTransferedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenBurned.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoTokenTokenBurnedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemExtrinsicFailed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoSystemExtrinsicFailedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsRevokeSubmitted.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsAssetsRevokeSubmittedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenReserved.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoTokenTokenReservedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSessionNewSession.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoSessionNewSessionWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesDeposit.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesDepositWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaNewAuthorities.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoGrandpaNewAuthoritiesWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemExtrinsicSuccess.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoSystemExtrinsicSuccessWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemKilledAccount.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoSystemKilledAccountWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsHostAssetsChanged.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsHostAssetsChangedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesDustLost.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesDustLostWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenHosted.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsTokenHostedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsRevokeConfirmed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsAssetsRevokeConfirmedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenRepatriated.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoTokenTokenRepatriatedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemNewAccount.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoSystemNewAccountWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenIssued.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoTokenTokenIssuedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsHostTokenChanged.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsHostTokenChangedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaPaused.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoGrandpaPausedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaResumed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoGrandpaResumedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenRevokeConfirmed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsTokenRevokeConfirmedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenDominatorClaimed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsTokenDominatorClaimedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesReserved.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesReservedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesUnreserved.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesUnreservedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesTransfer.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesTransferWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsDominatorClaimed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsDominatorClaimedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenUnreserved.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoTokenTokenUnreservedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesReserveRepatriated.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesReserveRepatriatedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemCodeUpdated.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoSystemCodeUpdatedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesBalanceSet.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesBalanceSetWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsHosted.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoReceiptsAssetsHostedWriter());
		map.put(tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesEndowed.class, new tech.uinb.tungus.codec.fusotao.writer.event.FusotaoBalancesEndowedWriter());
	}

	public ScaleWriter getWriter(Class clazz) {
		return map.get(clazz);
	}
}
