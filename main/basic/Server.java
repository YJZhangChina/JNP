package basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Server {

    private int port = 8080;

    public Server(int port) {
        this.port = port;
    }

    class SelectorServer {
        SelectorServer() throws IOException {
            Selector selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int readyChannel = selector.select();
                if (readyChannel == 0) {
                    continue;
                }
            }
        }
    }

}
