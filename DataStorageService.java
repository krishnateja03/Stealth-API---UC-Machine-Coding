package StealthAPI;

import java.util.*;

public class DataStorageService {
    private Map<Integer, Integer> jobDetailsMap;
    private PriorityQueue<Data> jobDetails;

    DataStorageService() {
        this.jobDetailsMap = new HashMap<>();
        this.jobDetails = new PriorityQueue<>((Data D1, Data D2) -> D2.getValue()-D1.getValue());
    }

    public String addNewJob(String jobId, String value) {
        int id = Integer.parseInt(jobId.substring(jobId.indexOf("=") + 1));
        int val = Integer.parseInt(value.substring(jobId.indexOf("=") + 1));
        System.out.println("Inserting job details : " + id + ", " + val + " into the jobdetails");
        jobDetails.offer(new Data(id, val));
        if(jobDetailsMap.containsKey(id)) {
            System.out.println("JOB ID already Exists");
        } else {
            jobDetailsMap.put(id, val);
        }
        return "{“stat”:”ok”}";
    }

    public List<Data> getAllJobs(int startValue) {
        List<Data> jobsFromStartValue = new ArrayList<>();
        boolean startValueFound = false;

        while(!jobDetails.isEmpty()) {
            int value = jobDetails.peek().getValue();
            if (value != startValue) {
                jobsFromStartValue.add(jobDetails.poll());
            } else {
                startValueFound = true;
                while(!jobDetails.isEmpty() && jobDetails.peek().getValue() == startValue) {
                    jobsFromStartValue.add(jobDetails.poll());
                }
                break;
            }
        }

        jobsFromStartValue.stream().forEach(data -> this.jobDetails.add(data));

        return startValueFound ? jobsFromStartValue : new ArrayList<>();
    }

    public String removeExistingJob(int jobIdToBeDeleted) {
        if(!jobDetailsMap.containsKey(jobIdToBeDeleted)) {
            return "{ERROR : 404 JobID not found}";
        }
        System.out.println("Removing the job with jobId " + jobIdToBeDeleted);

        jobDetailsMap.remove(jobIdToBeDeleted);

        while(!jobDetails.isEmpty()) {
            if(jobDetails.peek().getJobId() == jobIdToBeDeleted) {
                jobDetails.poll();
            }
        }

        for(Map.Entry<Integer, Integer> entry : jobDetailsMap.entrySet()) {
            jobDetails.add(new Data(entry.getKey(), entry.getValue()));
        }

        return "{“stat”:”ok”}";
    }
}
