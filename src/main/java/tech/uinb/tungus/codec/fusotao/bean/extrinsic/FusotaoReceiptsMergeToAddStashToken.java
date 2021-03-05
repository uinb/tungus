package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Hash256;
import java.util.List;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.MergeToAddStashToken(token:T::TokenId,fund_owner:T::AccountId,amount:Compact<TokenBalanceOf<T>>,digest:Vec<u8>)
public class FusotaoReceiptsMergeToAddStashToken extends ExtrinsicCall {
	private Hash256 Token;
	private Hash256 FundOwner;
	private DotAmount Amount;
	private List<Integer> Digest;

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