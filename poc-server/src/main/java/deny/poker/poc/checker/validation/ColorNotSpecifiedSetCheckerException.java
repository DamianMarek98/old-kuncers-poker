package deny.poker.poc.checker.validation;

class ColorNotSpecifiedSetCheckerException extends RuntimeException {
    public ColorNotSpecifiedSetCheckerException(String checkerName) {
        super("Colour should be specified to check in " + checkerName);
    }
}
