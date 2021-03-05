package tech.uinb.tungus.codec.fusotao.bean.extrinsic;

import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import java.util.Optional;
import io.emeraldpay.polkaj.types.Hash256;


//Receipts.QuitDominator(token:Option<T::TokenId>)
public class FusotaoReceiptsQuitDominator extends ExtrinsicCall {
	private Optional<Hash256> Token;

	public Optional<Hash256> getToken(){
		return this.Token;
	}

	public void setToken(Optional<Hash256> Token){
		this.Token = Token;
	}
}