package men.brakh.parser;

import men.brakh.Message;

public interface Parser {
    Message parse(String message);
    String stringify(Message message);
}
