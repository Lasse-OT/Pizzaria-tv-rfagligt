class OrderComparator implements java.util.Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {

        return a.getTime().compareTo(b.getTime());
    }
}