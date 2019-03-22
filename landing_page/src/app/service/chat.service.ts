import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { ApiAiClient } from "api-ai-javascript";
import { BehaviorSubject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { ShowChatResultsComponent } from "../components/show-chat-results/show-chat-results.component";

export class Message {
  constructor(public content: string, public sentBy: string) {}
}

@Injectable({
  providedIn: "root"
})
export class ChatService {
  variable: any;
  public baseUrl = "http://13.126.73.190:8092/chatbot/ChatBotService";
  readonly token = environment.dialogflow.angularBot;
  readonly client = new ApiAiClient({ accessToken: this.token });
  conversation = new BehaviorSubject<Message[]>([]);

  constructor(private http: HttpClient) {
    console.log("http search service is called");
  }

  public checkSearch(): any {
    console.log("inside service");

    this.variable = this.http.get(this.baseUrl);
    return this.variable;
  }
  // adds message to source
  update(msg: Message) {
    this.conversation.next([msg]);
  }

  // sends and receives msgs via dialogflow

  converse(msg: string) {
    const userMessage = new Message(msg, "user");
    this.update(userMessage);

    return this.client.textRequest(msg).then(res => {
      const speech = res.result.fulfillment.speech;
      const botMessage = new Message(speech, "bot");
      this.update(botMessage);
    });
  }

  talkWithBot(msg: string) {
    const userMessage = new Message(msg, "user");
    this.update(userMessage);
    return this.client.textRequest(msg).then(res => {
      const speech = res.result.fulfillment.speech;
      const botMessage = new Message(speech, "bot");
      this.update(botMessage);
    });
  }
  showChatResults(): any {
    console.log("inside chatfghj");
    return this.http.get(
      "http://13.126.73.190:8092/searchservice/api/v1/chatpolicy"
    );
  }
}
