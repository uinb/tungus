package tech.uinb.tungus.codec.fusotao;

import java.util.HashMap;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.ReaderRegistry;

public class FusotaoEventReaderRegistry implements ReaderRegistry {
	private HashMap<String, ScaleReader> map = new HashMap<>();
	public FusotaoEventReaderRegistry() {
		map.put("Token.TokenReserved", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoTokenTokenReservedReader());
		map.put("Balances.Transfer", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesTransferReader());
		map.put("Token.TokenIssued", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoTokenTokenIssuedReader());
		map.put("Receipts.HostAssetsChanged", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsHostAssetsChangedReader());
		map.put("System.ExtrinsicSuccess", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoSystemExtrinsicSuccessReader());
		map.put("System.CodeUpdated", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoSystemCodeUpdatedReader());
		map.put("Balances.Deposit", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesDepositReader());
		map.put("Receipts.TokenRevokeConfirmed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsTokenRevokeConfirmedReader());
		map.put("Receipts.AssetsRevokeSubmitted", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsAssetsRevokeSubmittedReader());
		map.put("System.NewAccount", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoSystemNewAccountReader());
		map.put("Balances.DustLost", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesDustLostReader());
		map.put("Balances.Unreserved", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesUnreservedReader());
		map.put("Token.TokenTransfered", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoTokenTokenTransferedReader());
		map.put("Receipts.AssetsRevokeConfirmed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsAssetsRevokeConfirmedReader());
		map.put("Session.NewSession", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoSessionNewSessionReader());
		map.put("Receipts.DominatorClaimed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsDominatorClaimedReader());
		map.put("System.ExtrinsicFailed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoSystemExtrinsicFailedReader());
		map.put("Token.TokenUnreserved", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoTokenTokenUnreservedReader());
		map.put("Receipts.TokenDominatorClaimed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsTokenDominatorClaimedReader());
		map.put("Token.TokenBurned", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoTokenTokenBurnedReader());
		map.put("Receipts.AssetsHosted", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsAssetsHostedReader());
		map.put("Receipts.TokenHosted", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsTokenHostedReader());
		map.put("Balances.Endowed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesEndowedReader());
		map.put("Balances.BalanceSet", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesBalanceSetReader());
		map.put("Grandpa.Paused", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoGrandpaPausedReader());
		map.put("Token.TokenRepatriated", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoTokenTokenRepatriatedReader());
		map.put("Balances.Reserved", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesReservedReader());
		map.put("System.KilledAccount", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoSystemKilledAccountReader());
		map.put("Receipts.TokenRevokeSubmitted", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsTokenRevokeSubmittedReader());
		map.put("Grandpa.Resumed", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoGrandpaResumedReader());
		map.put("Grandpa.NewAuthorities", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoGrandpaNewAuthoritiesReader());
		map.put("Balances.ReserveRepatriated", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoBalancesReserveRepatriatedReader());
		map.put("Receipts.HostTokenChanged", new tech.uinb.tungus.codec.fusotao.reader.event.FusotaoReceiptsHostTokenChangedReader());
	}

	public ScaleReader getReader(String name) {
		return map.get(name);
	}
}
