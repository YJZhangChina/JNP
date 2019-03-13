package reactor;

import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Reactor {

    private Map<Integer, IEventHandler> mapHandlers;
    private Selector demux;

    public Reactor() throws IOException {
        mapHandlers = new ConcurrentHashMap<Integer, IEventHandler>();
        demux = Selector.open();
    }

    public Selector getDemux() {
        return demux;
    }

    public void registerEventHandler(int event, IEventHandler handler) {
        mapHandlers.put(event, handler);
    }

    public void registerChannel(int event, SelectableChannel channel) throws IOException {
        channel.register(demux, event);
    }

    public void run() {
        try {
            while (true) {
                demux.select();
                Set<SelectionKey> readyHandles = demux.selectedKeys();
                Iterator<SelectionKey> iterator = readyHandles.iterator();
                while(iterator.hasNext()) {
                    SelectionKey handle = iterator.next();
                    if(handle.isAcceptable()) {
                        IEventHandler handler = mapHandlers.get(SelectionKey.OP_ACCEPT);
                        handler.handleEvent(handle);
                    }
                    if(handle.isReadable()) {
                        IEventHandler handler = mapHandlers.get(SelectionKey.OP_READ);
                        handler.handleEvent(handle);
                        iterator.remove();
                    }
                    if(handle.isWritable()) {
                        IEventHandler handler = mapHandlers.get(SelectionKey.OP_WRITE);
                        handler.handleEvent(handle);
                        iterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
