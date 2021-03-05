package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Balances.SetBalance(who:<T::Lookup as StaticLookup>::Source,new_free:Compact<T::Balance>,new_reserved:Compact<T::Balance>)
public class FusotaoBalancesSetBalance extends ExtrinsicCall {
	private Address Who;
	private DotAmount NewFree;
	private DotAmount NewReserved;

	public Address getWho(){
		return this.Who;
	}

	public void setWho(Address Who){
		this.Who = Who;
	}

	public DotAmount getNewFree(){
		return this.NewFree;
	}

	public void setNewFree(DotAmount NewFree){
		this.NewFree = NewFree;
	}

	public DotAmount getNewReserved(){
		return this.NewReserved;
	}

	public void setNewReserved(DotAmount NewReserved){
		this.NewReserved = NewReserved;
	}
}