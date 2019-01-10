import { UserAdministrationModule } from './user-administration.module';

describe('UserAdministrationModule', () => {
  let userAdministrationModule: UserAdministrationModule;

  beforeEach(() => {
    userAdministrationModule = new UserAdministrationModule();
  });

  it('should create an instance', () => {
    expect(userAdministrationModule).toBeTruthy();
  });
});
