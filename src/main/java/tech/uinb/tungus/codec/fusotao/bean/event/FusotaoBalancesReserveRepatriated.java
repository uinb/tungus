package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.fusotao.type.FusotaoBalanceStatus;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;


//Balances.ReserveRepatriated(AccountId,AccountId,Balance,Status)
public class FusotaoBalancesReserveRepatriated extends EventRecord {
	private Address param0;
	private Address param1;
	private DotAmount param2;
	private FusotaoBalanceStatus param3;

	public Address getParam0(){
		return this.param0;
	}

	public void setParam0(Address param0){
		this.param0 = param0;
	}

	public Address getParam1(){
		return this.param1;
	}

	public void setParam1(Address param1){
		this.param1 = param1;
	}

	public DotAmount getParam2(){
		return this.param2;
	}

	public void setParam2(DotAmount param2){
		this.param2 = param2;
	}

	public FusotaoBalanceStatus getParam3(){
		return this.param3;
	}

	public void setParam3(FusotaoBalanceStatus param3){
		this.param3 = param3;
	}
}