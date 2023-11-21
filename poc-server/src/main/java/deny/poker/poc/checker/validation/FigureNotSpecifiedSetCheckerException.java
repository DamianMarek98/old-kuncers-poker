package deny.poker.poc.checker.validation;

class FigureNotSpecifiedSetCheckerException extends RuntimeException {
    public FigureNotSpecifiedSetCheckerException(String checkerName) {
        super("Figure should be specified to check in " + checkerName);
    }
}
