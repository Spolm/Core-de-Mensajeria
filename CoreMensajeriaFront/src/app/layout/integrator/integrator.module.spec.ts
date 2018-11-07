import { IntegratorModule } from './integrator.module';

describe('IntegratorModule', () => {
  let integratorModule: IntegratorModule;

  beforeEach(() => {
    integratorModule = new IntegratorModule();
  });

  it('should create an instance', () => {
    expect(integratorModule).toBeTruthy();
  });
});
