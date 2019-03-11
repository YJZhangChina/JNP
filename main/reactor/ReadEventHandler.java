package reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadEventHandler implements IEventHandler {

    private Selector demux;
    private ByteBuffer input;

    public ReadEventHandler(Selector demux) {
        this.demux = demux;
        input = ByteBuffer.allocate(2048);
    }

    public void handleEvent(SelectionKey handle) throws IOException {
        SocketChannel socketChannel = (SocketChannel) handle.channel();
        socketChannel.read(input);
        input.flip();
        byte[] bytes = new byte[input.limit()];
        input.flip();
        socketChannel.register(demux, SelectionKey.OP_WRITE, input);
    }

}
