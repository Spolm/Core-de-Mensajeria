import { RecoverPassModule } from './recover-pass.module';

describe('RecoverPassModule', () => {
  let recoverPassModule: RecoverPassModule;

  beforeEach(() => {
    recoverPassModule = new RecoverPassModule();
  });

  it('should create an instance', () => {
    expect(recoverPassModule).toBeTruthy();
  });
});
