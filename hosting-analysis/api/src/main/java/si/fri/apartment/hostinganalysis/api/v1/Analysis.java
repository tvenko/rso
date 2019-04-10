package si.fri.apartment.hostinganalysis.api.v1;

public class Analysis {

    private int daily_hostings;
    private int monthly_hostings;
    private int yearly_hostings;

    public Analysis(int daily_hostings, int monthly_hostings, int yearly_hostings) {
        this.daily_hostings = daily_hostings;
        this.monthly_hostings = monthly_hostings;
        this.yearly_hostings = yearly_hostings;
    }

    public int getDaily_hostings() {
        return daily_hostings;
    }

    public void setDaily_hostings(int daily_hostings) {
        this.daily_hostings = daily_hostings;
    }

    public int getMonthly_hostings() {
        return monthly_hostings;
    }

    public void setMonthly_hostings(int monthly_hostings) {
        this.monthly_hostings = monthly_hostings;
    }

    public int getYearly_hostings() {
        return yearly_hostings;
    }

    public void setYearly_hostings(int yearly_hostings) {
        this.yearly_hostings = yearly_hostings;
    }
}
