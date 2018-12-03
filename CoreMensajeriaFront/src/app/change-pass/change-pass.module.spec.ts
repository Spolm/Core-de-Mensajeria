import { ChangePassModule } from './change-pass.module';

describe('ChangePassModule', () => {
  let changePassModule: ChangePassModule;

  beforeEach(() => {
    changePassModule = new ChangePassModule();
  });

  it('should create an instance', () => {
    expect(changePassModule).toBeTruthy();
  });
});
