package org.example;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase{

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {

        for(int i = 0; i < 5; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(request);

            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.
                    HelloResponse.newBuilder().setGreeting("Hello from server, " + request.getName() + "with hobbies " + request.getHobbies())
                    .build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();

    }
}
