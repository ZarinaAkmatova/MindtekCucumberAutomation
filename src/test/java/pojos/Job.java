package pojos;

public class Job {
    private String jobId;
    private Double salary;
    private String title;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", salary=" + salary +
                ", title='" + title + '\'' +
                '}';
    }
}
