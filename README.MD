cabbit-example
=============

A simple example to demonstrate how to use cabbit.

### Instructions
0. Get an instance of RabbitMQ

If you don't have one, you can use docker: `docker run --hostname my-rabbit --name some-rabbit -p 5672:5672 rabbitmq:3`
Use your RabbitMQ host and port in the following examples, I use 'localhost' and '5672'

1. Start `ConsumeApp`, observe output
```sbt
sbt:cabbit-example> runMain ru.delimobil.cabbit.example.ConsumeApp localhost 5672

consumer: started
``` 
2. Start `PublishApp` in another instance of sbt
```sbt
sbt:cabbit-example> runMain ru.delimobil.cabbit.example.PublishApp localhost 5672

publisher: published
``` 
3. Notice that line `consumer: {"from":"TeaserPublishApp","msg":"hello from cabbit"}` was added to  `ConsumeApp` log.
4. Start `PublishApp` again

Notice that `ConsumerApp` is now terminated, two lines
```
consumer: {"from":"TeaserPublishApp","msg":"hello from cabbit"}
consumer: completed
```
were added to `ConsumerApp` log.

Now you can look at how this behavior is implemented.

### LICENSE ###
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
