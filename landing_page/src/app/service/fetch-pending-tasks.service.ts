import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { PendingTasks } from "../components/pending-tasks";
import { Task } from "../components//task";

@Injectable({
  providedIn: "root"
})
export class FetchPendingTasksService {
  settlementUrl = 'http://13.126.73.190:8092/settlement/api/v1/';
  // settlementUrl = 'http://localhost:8092/settlement/api/v1/';
  fetchAllPortingRequestsUrl = "pendingtasks/";
  addANewPendingTaskUrl = "pendingtask/";
  modifyStatusOfPendingTaskUrl = "pendingtask/";
  postNewPendingTaskUrl = "pendingtask/";
  fetchPendingTasksUrl = 'pendingtasks/';

  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: "my-auth-token"
    })
  };
  constructor(private httpClient: HttpClient) {}
  postANewPendingTasks(pendingTasks: PendingTasks): Observable<PendingTasks> {
    return this.httpClient.post<PendingTasks>(this.settlementUrl + this.postNewPendingTaskUrl, pendingTasks);
  }
  fetchAllPortingRequests(currentCompanyName): Observable<PendingTasks[]> {
    return this.httpClient.get<PendingTasks[]>(
      this.settlementUrl + this.fetchAllPortingRequestsUrl + currentCompanyName
    );
  }
  getPendingTasksById(portingRequestId: number): Observable<PendingTasks[]> {
    return this.httpClient.get<PendingTasks[]>(this.settlementUrl + this.fetchPendingTasksUrl + 'portingRequestId=' + portingRequestId);
  }
  addANewPendingTask(
    pendingtasksId: number,
    newTask: Task
  ): Observable<PendingTasks> {
    console.log(
      this.settlementUrl + this.addANewPendingTaskUrl + pendingtasksId
    );
    return this.httpClient.put<PendingTasks>(
      this.settlementUrl + this.addANewPendingTaskUrl + pendingtasksId,
      newTask,
      this.httpOptions
    );
  }
  modifyStatusOfTask(
    taskStatus: boolean,
    pendingTasksId: number,
    taskName: string
  ): Observable<PendingTasks> {
    if (taskStatus == true) {
      console.log(
        this.settlementUrl +
          this.modifyStatusOfPendingTaskUrl +
          pendingTasksId +
          "/" +
          taskName +
          "/" +
          taskStatus
      );
      return this.httpClient.put<PendingTasks>(
        this.settlementUrl +
          this.modifyStatusOfPendingTaskUrl +
          pendingTasksId +
          "/" +
          taskName +
          "/" +
          "true",
        taskStatus,
        this.httpOptions
      );
    } else {
      console.log(
        this.settlementUrl +
          this.modifyStatusOfPendingTaskUrl +
          pendingTasksId +
          "/" +
          taskName +
          "/" +
          taskStatus
      );
      return this.httpClient.put<PendingTasks>(
        this.settlementUrl +
          this.modifyStatusOfPendingTaskUrl +
          pendingTasksId +
          "/" +
          taskName +
          "/" +
          "false",
        taskStatus,
        this.httpOptions
      );
    }
  }
}
