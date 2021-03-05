package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.ClaimDominator(amount:Compact<BalanceOf<T>>)
public class FusotaoReceiptsClaimDominator extends ExtrinsicCall {
	private DotAmount Amount;

	public DotAmount getAmount(){
		return this.Amount;
	}

	public void setAmount(DotAmount Amount){
		this.Amount = Amount;
	}
}