package tech.uinb.tungus.service;

import tech.uinb.tungus.entity.TableMeta;

public interface TableMetaService {
    String BLOCK_HEADER = "t_block_header";
    String KEY_ID_MAP = "t_hash_id_map";
    String DIGEST_LOG = "t_digest_log";
    String EXTRINSICS = "t_extrinsics";
    String ACCOUNT = "t_account";
    String ACCOUNT_EXTRINSIC = "t_account_extrinsic";
    String ACCOUNT_TRANSACTION = "t_account_transaction";
    String ACCOUNT_STASH = "t_account_stash";
    String BLOCK_EXTRINSIC = "t_block_extrinsic";
    String EVENT = "t_event";
    String EXTRINSIC_EVENT = "t_ext_event";

    TableMeta getByPrefix(String prefix);
}
