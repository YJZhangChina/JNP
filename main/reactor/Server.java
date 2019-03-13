package reactor;

import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class Server {

    public void startServer(int port) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(port));
        server.configureBlocking(false);

        Reactor reactor = new Reactor();
        reactor.registerChannel(SelectionKey.OP_ACCEPT, server);
        reactor.registerEventHandler(SelectionKey.OP_ACCEPT, new AcceptEventHandler(reactor.getDemux()));
        reactor.registerEventHandler(SelectionKey.OP_READ, new ReadEventHandler(reactor.getDemux()));
        reactor.registerEventHandler(SelectionKey.OP_WRITE, new WriteEventHandler());
        reactor.run();
    }

    public static void main(String[] args) {
        try{
            new Server().startServer(8080);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
