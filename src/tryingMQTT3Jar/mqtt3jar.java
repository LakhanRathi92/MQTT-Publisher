package tryingMQTT3Jar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class mqtt3jar {
		public static void main(String args[]) throws IOException{
			String topic        = "MQTT Examples";
	        String content      = "2.0";
	        int qos             = 2;
	        String broker       = "ws://localhost:8080";
	        String clientId     = "JavaSample";
	        MemoryPersistence persistence = new MemoryPersistence();

	        try {
	            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	            MqttConnectOptions connOpts = new MqttConnectOptions();
	            connOpts.setCleanSession(true);
	            System.out.println("Connecting to broker: "+broker);
	            sampleClient.connect(connOpts);
	            System.out.println("Connected");
	            System.out.println("Publishing message: "+ content);
	            
	            /* creating an entry in Binary Translation Table */
	            BinaryTranslationTable table = new BinaryTranslationTable();
	    		TableRow row1 = new TableRow();	    		
	    		row1.createRow(123, (short)0, (short)3);
	            table.addElement(row1);
	            byte[] serializedTable = null;
				serializedTable = SerDes.serialize(table);
				
				/*Actual reading*/
				byte[] reading = content.getBytes();	            
				
				/*determining the size to be encoded in first byte/(s)*/
				int size = reading.length+serializedTable.length;
				
				/* Encode size information using variable length encoding scheme of MQTT */
	            byte[] encodedByte = VariableLengthEnc_Dec.Encode(size);
	            
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	            out.write(encodedByte);
	            out.write(reading);
	          
	            
	            /*Regular MQTT Publish*/	            
	            MqttMessage message = new MqttMessage(out.toByteArray());
	            message.setQos(qos);
	            sampleClient.publish(topic, message);
	            System.out.println("Message published");
	            sampleClient.disconnect();
	            System.out.println("Disconnected");
	            System.exit(0);
	        } catch(MqttException me) {
	            System.out.println("reason "+me.getReasonCode());
	            System.out.println("msg "+me.getMessage());
	            System.out.println("loc "+me.getLocalizedMessage());
	            System.out.println("cause "+me.getCause());
	            System.out.println("excep "+me);
	            me.printStackTrace();
	        }
	    }
}