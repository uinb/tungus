package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;


//Receipts.ConfirmWithdraw(fund_owner:T::AccountId)
public class FusotaoReceiptsConfirmWithdraw extends ExtrinsicCall {
	private Hash256 FundOwner;

	public Hash256 getFundOwner(){
		return this.FundOwner;
	}

	public void setFundOwner(Hash256 FundOwner){
		this.FundOwner = FundOwner;
	}
}