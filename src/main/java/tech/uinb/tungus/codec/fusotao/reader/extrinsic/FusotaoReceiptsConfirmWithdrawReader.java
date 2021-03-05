package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsConfirmWithdraw;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.types.Hash256;

public class FusotaoReceiptsConfirmWithdrawReader implements ScaleReader<FusotaoReceiptsConfirmWithdraw> {
	@Override
	public FusotaoReceiptsConfirmWithdraw read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsConfirmWithdraw();
		result.setFundOwner(new Hash256(rdr.readUint256()));
		return result;
	}
}
