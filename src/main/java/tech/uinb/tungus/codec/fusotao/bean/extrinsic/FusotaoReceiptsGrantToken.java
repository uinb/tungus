package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import java.math.BigInteger;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Receipts.GrantToken(token:T::TokenId,dominator:<T::Lookup as StaticLookup>::Source,amount:Compact<TokenBalanceOf<T>>,memo:UID)
public class FusotaoReceiptsGrantToken extends ExtrinsicCall {
	private Hash256 Token;
	private Address Dominator;
	private DotAmount Amount;
	private BigInteger Memo;

	public Hash256 getToken(){
		return this.Token;
	}

	public void setToken(Hash256 Token){
		this.Token = Token;
	}

	public Address getDominator(){
		return this.Dominator;
	}

	public void setDominator(Address Dominator){
		this.Dominator = Dominator;
	}

	public DotAmount getAmount(){
		return this.Amount;
	}

	public void setAmount(DotAmount Amount){
		this.Amount = Amount;
	}

	public BigInteger getMemo(){
		return this.Memo;
	}

	public void setMemo(BigInteger Memo){
		this.Memo = Memo;
	}
}