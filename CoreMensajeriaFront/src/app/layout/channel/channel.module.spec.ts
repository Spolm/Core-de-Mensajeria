import { ChannelModule } from './channel.module';

describe('ChannelModule', () => {
  let channelModule: ChannelModule;

  beforeEach(() => {
    channelModule = new ChannelModule();
  });

  it('should create an instance', () => {
    expect(channelModule).toBeTruthy();
  });
});
