//package kg.itacademy.gsg.fcm;
//
//@Service
//public class FCMService {
//
//    ...
//
//    public void sendMessageWithoutData(PushNotificationRequest request)
//            throws InterruptedException, ExecutionException {
//        Message message = getPreconfiguredMessageWithoutData(request);
//        String response = sendAndGetResponse(message);
//        logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
//    }
//
//    ...
//
//    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
//        return FirebaseMessaging.getInstance().sendAsync(message).get();
//    }
//
//    private AndroidConfig getAndroidConfig(String topic) {
//        return AndroidConfig.builder()
//                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
//                .setPriority(AndroidConfig.Priority.HIGH)
//                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
//                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
//    }
//
//    private ApnsConfig getApnsConfig(String topic) {
//        return ApnsConfig.builder()
//                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
//    }
//
//    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
//        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
//                .build();
//    }
//
//    ...
//
//    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
//        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
//        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
//        return Message.builder()
//                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
//                        new Notification(request.getTitle(), request.getMessage()));
//    }
//
//
//}