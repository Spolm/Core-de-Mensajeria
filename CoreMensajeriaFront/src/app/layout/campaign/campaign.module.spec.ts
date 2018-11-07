import { CampaignModule } from './campaign.module';

describe('CampaignModule', () => {
  let campaignModule: CampaignModule;

  beforeEach(() => {
    campaignModule = new CampaignModule();
  });

  it('should create an instance', () => {
    expect(campaignModule).toBeTruthy();
  });
});
