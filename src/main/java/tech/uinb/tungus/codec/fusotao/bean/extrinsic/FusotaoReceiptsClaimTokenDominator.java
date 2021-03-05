package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.ClaimTokenDominator(token:T::TokenId,amount:Compact<TokenBalanceOf<T>>)
public class FusotaoReceiptsClaimTokenDominator extends ExtrinsicCall {
	private Hash256 Token;
	private DotAmount Amount;

	public Hash256 getToken(){
		return this.Token;
	}

	public void setToken(Hash256 Token){
		this.Token = Token;
	}

	public DotAmount getAmount(){
		return this.Amount;
	}

	public void setAmount(DotAmount Amount){
		this.Amount = Amount;
	}
}