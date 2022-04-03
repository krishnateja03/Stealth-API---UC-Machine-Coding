package StealthAPI;

import java.util.List;
import java.util.Scanner;

public class DataStorage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataStorageService dataStorageService = new DataStorageService();
        while(scanner.hasNext()) {
            String request = scanner.nextLine();
            if(request.equals("exit")) {
                scanner.close();
            }
            String requestType = request.split("[?]")[0];
            String queryParams = request.split("[?]")[1];
            if(requestType.equals("GET /add")) {
                String value = queryParams.split("[&]")[0];
                String jobId = queryParams.split("[&]")[1];
                System.out.println(dataStorageService.addNewJob(jobId, value));
                System.out.println("End of GET /add request!");
            } else if (requestType.equals("GET /all")) {
                int startValue = Integer.parseInt(queryParams.substring(queryParams.indexOf("=") + 1));
                List<Data> jobsFromStartValue = dataStorageService.getAllJobs(startValue);
                if (!jobsFromStartValue.isEmpty()) {
                    System.out.println("Jobs from start value : " + startValue);
                    for (Data data : jobsFromStartValue) {
                        System.out.println(data.getJobId() + ", " + data.getValue());
                    }
                    System.out.println("Fetched job details from startValue successfully!");
                } else {
                    System.out.println("Error : startValue doesn't exist in Database");
                }
                System.out.println("End of GET /all request!");
            } else {
                int jobIdToBeRemoved = Integer.parseInt(queryParams.substring(queryParams.indexOf("=") + 1));
                System.out.println(dataStorageService.removeExistingJob(jobIdToBeRemoved));
                System.out.println("End of GET /remove request!");
            }
        }
    }
}
