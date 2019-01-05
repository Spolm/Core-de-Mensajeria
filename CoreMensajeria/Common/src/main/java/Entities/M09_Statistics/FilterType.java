package Entities.M09_Statistics;

public enum FilterType {
    company {
        @Override
        public String value() {
            return "com_name";
        }
    },
    campaign {
        @Override
        public String value() {
            return "cam_name";
        }
    },
    channel {
        @Override
        public String value() {
            return "cha_name";
        }
    },
    integrator {
        @Override
        public String value() {
            return "int_name";
        }
    };

    public abstract String value();
}
