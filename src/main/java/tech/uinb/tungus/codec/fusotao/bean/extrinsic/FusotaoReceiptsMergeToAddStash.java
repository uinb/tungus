package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;
import java.util.List;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.MergeToAddStash(fund_owner:T::AccountId,amount:Compact<BalanceOf<T>>,digest:Vec<u8>)
public class FusotaoReceiptsMergeToAddStash extends ExtrinsicCall {
	private Hash256 FundOwner;
	private DotAmount Amount;
	private List<Integer> Digest;

	public Hash256 getFundOwner(){
		return this.FundOwner;
	}

	public void setFundOwner(Hash256 FundOwner){
		this.FundOwner = FundOwner;
	}

	public DotAmount getAmount(){
		return this.Amount;
	}

	public void setAmount(DotAmount Amount){
		this.Amount = Amount;
	}

	public List<Integer> getDigest(){
		return this.Digest;
	}

	public void setDigest(List<Integer> Digest){
		this.Digest = Digest;
	}
}