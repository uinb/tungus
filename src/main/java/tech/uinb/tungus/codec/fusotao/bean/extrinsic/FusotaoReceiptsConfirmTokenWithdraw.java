package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;


//Receipts.ConfirmTokenWithdraw(token:T::TokenId,fund_owner:T::AccountId)
public class FusotaoReceiptsConfirmTokenWithdraw extends ExtrinsicCall {
	private Hash256 Token;
	private Hash256 FundOwner;

	public Hash256 getToken(){
		return this.Token;
	}

	public void setToken(Hash256 Token){
		this.Token = Token;
	}

	public Hash256 getFundOwner(){
		return this.FundOwner;
	}

	public void setFundOwner(Hash256 FundOwner){
		this.FundOwner = FundOwner;
	}
}