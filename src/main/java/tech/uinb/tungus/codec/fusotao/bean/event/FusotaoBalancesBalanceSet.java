package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Balances.BalanceSet(AccountId,Balance,Balance)
public class FusotaoBalancesBalanceSet extends EventRecord {
	private Address param0;
	private DotAmount param1;
	private DotAmount param2;

	public Address getParam0(){
		return this.param0;
	}

	public void setParam0(Address param0){
		this.param0 = param0;
	}

	public DotAmount getParam1(){
		return this.param1;
	}

	public void setParam1(DotAmount param1){
		this.param1 = param1;
	}

	public DotAmount getParam2(){
		return this.param2;
	}

	public void setParam2(DotAmount param2){
		this.param2 = param2;
	}
}