package reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class WriteEventHandler implements IEventHandler {

    public void handleEvent(SelectionKey handle) throws IOException {
        SocketChannel socketChannel = (SocketChannel) handle.channel();
        ByteBuffer input = (ByteBuffer) handle.attachment();
        socketChannel.write(input);
        socketChannel.close();
    }

}
