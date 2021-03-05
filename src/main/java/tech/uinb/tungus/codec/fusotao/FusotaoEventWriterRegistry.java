package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.WriterRegistry;
import tech.uinb.tungus.codec.fusotao.bean.event.*;
import tech.uinb.tungus.codec.fusotao.writer.event.*;

public class FusotaoEventWriterRegistry implements WriterRegistry {
	private HashMap<Class, ScaleWriter> map = new HashMap<>();
	public FusotaoEventWriterRegistry() {
		map.put(FusotaoTokenTokenBurned.class, new FusotaoTokenTokenBurnedWriter());
		map.put(FusotaoBalancesBalanceSet.class, new FusotaoBalancesBalanceSetWriter());
		map.put(FusotaoTokenTokenReserved.class, new FusotaoTokenTokenReservedWriter());
		map.put(FusotaoReceiptsAssetsHosted.class, new FusotaoReceiptsAssetsHostedWriter());
		map.put(FusotaoSystemExtrinsicFailed.class, new FusotaoSystemExtrinsicFailedWriter());
		map.put(FusotaoGrandpaNewAuthorities.class, new FusotaoGrandpaNewAuthoritiesWriter());
		map.put(FusotaoReceiptsAssetsRevokeSubmitted.class, new FusotaoReceiptsAssetsRevokeSubmittedWriter());
		map.put(FusotaoBalancesReserveRepatriated.class, new FusotaoBalancesReserveRepatriatedWriter());
		map.put(FusotaoReceiptsAssetsRevokeConfirmed.class, new FusotaoReceiptsAssetsRevokeConfirmedWriter());
		map.put(FusotaoReceiptsDominatorClaimed.class, new FusotaoReceiptsDominatorClaimedWriter());
		map.put(FusotaoTokenTokenIssued.class, new FusotaoTokenTokenIssuedWriter());
		map.put(FusotaoSystemCodeUpdated.class, new FusotaoSystemCodeUpdatedWriter());
		map.put(FusotaoGrandpaResumed.class, new FusotaoGrandpaResumedWriter());
		map.put(FusotaoSystemNewAccount.class, new FusotaoSystemNewAccountWriter());
		map.put(FusotaoTokenTokenRepatriated.class, new FusotaoTokenTokenRepatriatedWriter());
		map.put(FusotaoBalancesDustLost.class, new FusotaoBalancesDustLostWriter());
		map.put(FusotaoSystemExtrinsicSuccess.class, new FusotaoSystemExtrinsicSuccessWriter());
		map.put(FusotaoGrandpaPaused.class, new FusotaoGrandpaPausedWriter());
		map.put(FusotaoReceiptsTokenDominatorClaimed.class, new FusotaoReceiptsTokenDominatorClaimedWriter());
		map.put(FusotaoBalancesUnreserved.class, new FusotaoBalancesUnreservedWriter());
		map.put(FusotaoBalancesReserved.class, new FusotaoBalancesReservedWriter());
		map.put(FusotaoBalancesEndowed.class, new FusotaoBalancesEndowedWriter());
		map.put(FusotaoReceiptsTokenRevokeSubmitted.class, new FusotaoReceiptsTokenRevokeSubmittedWriter());
		map.put(FusotaoSystemKilledAccount.class, new FusotaoSystemKilledAccountWriter());
		map.put(FusotaoSessionNewSession.class, new FusotaoSessionNewSessionWriter());
		map.put(FusotaoBalancesDeposit.class, new FusotaoBalancesDepositWriter());
		map.put(FusotaoBalancesTransfer.class, new FusotaoBalancesTransferWriter());
		map.put(FusotaoReceiptsHostAssetsChanged.class, new FusotaoReceiptsHostAssetsChangedWriter());
		map.put(FusotaoReceiptsTokenRevokeConfirmed.class, new FusotaoReceiptsTokenRevokeConfirmedWriter());
		map.put(FusotaoReceiptsTokenHosted.class, new FusotaoReceiptsTokenHostedWriter());
		map.put(FusotaoReceiptsHostTokenChanged.class, new FusotaoReceiptsHostTokenChangedWriter());
		map.put(FusotaoTokenTokenTransfered.class, new FusotaoTokenTokenTransferedWriter());
		map.put(FusotaoTokenTokenUnreserved.class, new FusotaoTokenTokenUnreservedWriter());
	}

	public ScaleWriter getWriter(Class clazz) {
		return map.get(clazz);
	}
}
