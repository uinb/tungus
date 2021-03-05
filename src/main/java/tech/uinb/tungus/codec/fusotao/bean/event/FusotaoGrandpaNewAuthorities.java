package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.fusotao.type.AuthorityList;


//Grandpa.NewAuthorities(AuthorityList)
public class FusotaoGrandpaNewAuthorities extends EventRecord {
	private AuthorityList param0;

	public AuthorityList getParam0(){
		return this.param0;
	}

	public void setParam0(AuthorityList param0){
		this.param0 = param0;
	}
}