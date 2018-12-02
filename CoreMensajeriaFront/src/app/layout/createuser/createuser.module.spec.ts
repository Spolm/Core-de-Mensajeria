import { CreateuserModule } from './createuser.module';

describe('CreateuserModule', () => {
  let createuserModule: CreateuserModule;

  beforeEach(() => {
    createuserModule = new CreateuserModule();
  });

  it('should create an instance', () => {
    expect(createuserModule).toBeTruthy();
  });
});
