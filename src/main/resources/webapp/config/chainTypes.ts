export default {
  Address: 'MultiAddress',
  LookupSource: 'MultiAddress',
  BlockNumber: 'u32',
  Signature: 'MultiSignature',
  TokenInfo: {
    total: 'Balance',
    symbol: 'String',
  },
  TokenId: 'Hash',
  Receipt: {
    value: 'Balance',
  },
  Pair: '(AccountId, AccountId)',
  AccountData: {
    free: 'Balance',
    reserved: 'Balance',
    misc_frozen: 'Balance',
    fee_frozen: 'Balance',
  },
  TokenAccountData: {
    free: 'Balance',
    reserved: 'Balance',
  },
  Dominator: {
    total_pledged: 'Balance',
    total_hosted: 'Balance',
    status: 'DominatorStatus',
    assets_type: 'Option<TokenId>',
  },
  DominatorStatus: {
    _enum: ['Active', 'Closing', 'Banned'],
  },
  UID: 'u128',
  AssetId: 'u32',
  Voter: {
    round: 'u32',
    account: 'AccountId',
    amount: 'Balance',
    pledger: 'Vec<Pledger<AccountId, BlockNumber, Balance>>',
  },
  Pledger: {
    account: 'AccountId',
    block_number: 'BlockNumber',
    amount: 'Balance',
  },
  MemberOf: 'Vec<Voter>',
  HostingPair: '(AccountId, AccountId)',
};
