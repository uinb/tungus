package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.ReaderRegistry;
import tech.uinb.tungus.codec.fusotao.reader.event.*;

public class FusotaoEventReaderRegistry implements ReaderRegistry {
	private HashMap<String, ScaleReader> map = new HashMap<>();
	public FusotaoEventReaderRegistry() {
		map.put("Token.TokenReserved", new FusotaoTokenTokenReservedReader());
		map.put("Balances.Transfer", new FusotaoBalancesTransferReader());
		map.put("Token.TokenIssued", new FusotaoTokenTokenIssuedReader());
		map.put("Receipts.HostAssetsChanged", new FusotaoReceiptsHostAssetsChangedReader());
		map.put("System.ExtrinsicSuccess", new FusotaoSystemExtrinsicSuccessReader());
		map.put("System.CodeUpdated", new FusotaoSystemCodeUpdatedReader());
		map.put("Balances.Deposit", new FusotaoBalancesDepositReader());
		map.put("Receipts.TokenRevokeConfirmed", new FusotaoReceiptsTokenRevokeConfirmedReader());
		map.put("Receipts.AssetsRevokeSubmitted", new FusotaoReceiptsAssetsRevokeSubmittedReader());
		map.put("System.NewAccount", new FusotaoSystemNewAccountReader());
		map.put("Balances.DustLost", new FusotaoBalancesDustLostReader());
		map.put("Balances.Unreserved", new FusotaoBalancesUnreservedReader());
		map.put("Token.TokenTransfered", new FusotaoTokenTokenTransferedReader());
		map.put("Receipts.AssetsRevokeConfirmed", new FusotaoReceiptsAssetsRevokeConfirmedReader());
		map.put("Session.NewSession", new FusotaoSessionNewSessionReader());
		map.put("Receipts.DominatorClaimed", new FusotaoReceiptsDominatorClaimedReader());
		map.put("System.ExtrinsicFailed", new FusotaoSystemExtrinsicFailedReader());
		map.put("Token.TokenUnreserved", new FusotaoTokenTokenUnreservedReader());
		map.put("Receipts.TokenDominatorClaimed", new FusotaoReceiptsTokenDominatorClaimedReader());
		map.put("Token.TokenBurned", new FusotaoTokenTokenBurnedReader());
		map.put("Receipts.AssetsHosted", new FusotaoReceiptsAssetsHostedReader());
		map.put("Receipts.TokenHosted", new FusotaoReceiptsTokenHostedReader());
		map.put("Balances.Endowed", new FusotaoBalancesEndowedReader());
		map.put("Balances.BalanceSet", new FusotaoBalancesBalanceSetReader());
		map.put("Grandpa.Paused", new FusotaoGrandpaPausedReader());
		map.put("Token.TokenRepatriated", new FusotaoTokenTokenRepatriatedReader());
		map.put("Balances.Reserved", new FusotaoBalancesReservedReader());
		map.put("System.KilledAccount", new FusotaoSystemKilledAccountReader());
		map.put("Receipts.TokenRevokeSubmitted", new FusotaoReceiptsTokenRevokeSubmittedReader());
		map.put("Grandpa.Resumed", new FusotaoGrandpaResumedReader());
		map.put("Grandpa.NewAuthorities", new FusotaoGrandpaNewAuthoritiesReader());
		map.put("Balances.ReserveRepatriated", new FusotaoBalancesReserveRepatriatedReader());
		map.put("Receipts.HostTokenChanged", new FusotaoReceiptsHostTokenChangedReader());
	}

	public ScaleReader getReader(String name) {
		return map.get(name);
	}
}
