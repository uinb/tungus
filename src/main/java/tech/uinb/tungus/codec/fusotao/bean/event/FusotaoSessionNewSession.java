package tech.uinb.tungus.codec.fusotao.bean.event;

import tech.uinb.tungus.codec.EventRecord;


//Session.NewSession(SessionIndex)
public class FusotaoSessionNewSession extends EventRecord {
	private Long param0;

	public Long getParam0(){
		return this.param0;
	}

	public void setParam0(Long param0){
		this.param0 = param0;
	}
}