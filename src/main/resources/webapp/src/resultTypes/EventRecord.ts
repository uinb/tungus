import { Vec } from '@polkadot/types/codec/Vec';
import { u8aToU8a } from '@polkadot/util';
import type { Registry } from '@polkadot/types/types';
import { EventRecord } from '@polkadot/types/interfaces/system/types';
export default class IEventRecord extends Vec<EventRecord> {
  constructor(registry: Registry, data: string) {
    super(registry, 'EventRecord', u8aToU8a(data));
  }
}
