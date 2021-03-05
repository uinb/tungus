package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import java.util.List;
import io.emeraldpay.polkaj.types.DotAmount;


//Token.Issue(total:Compact<T::Balance>,symbol:Vec<u8>)
public class FusotaoTokenIssue extends ExtrinsicCall {
	private DotAmount Total;
	private List<Integer> Symbol;

	public DotAmount getTotal(){
		return this.Total;
	}

	public void setTotal(DotAmount Total){
		this.Total = Total;
	}

	public List<Integer> getSymbol(){
		return this.Symbol;
	}

	public void setSymbol(List<Integer> Symbol){
		this.Symbol = Symbol;
	}
}