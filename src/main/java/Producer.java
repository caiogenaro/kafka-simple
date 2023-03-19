import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducer<String, String>  producer = new KafkaProducer<String, String>(properties());



        ProducerRecord record = new ProducerRecord("topicTesteCaio", "id:  1", "mensagem teste");

        Callback callback = (data, error) -> {
            if(error != null){
                error.printStackTrace();
                return;
            }

            System.out.println("Mensagem publicada com sucesso");
        };

        producer.send(record, callback).get();


    }



    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        return properties;
    }

}
