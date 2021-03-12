package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsConfirmTokenWithdraw;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.types.Hash256;

public class FusotaoReceiptsConfirmTokenWithdrawReader implements ScaleReader<FusotaoReceiptsConfirmTokenWithdraw> {
	@Override
	public FusotaoReceiptsConfirmTokenWithdraw read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsConfirmTokenWithdraw();
		result.setToken(new Hash256(rdr.readUint256()));
		result.setFundOwner(new Hash256(rdr.readUint256()));
		return result;
	}
}
