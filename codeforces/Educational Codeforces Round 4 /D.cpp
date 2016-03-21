#include <stdio.h>
#include <algorithm>
#include <vector>
using namespace std;

typedef pair<int, int> Event;
const int OPENING = -1;
const int CLOSING = 1;

struct EventSorter {
	bool operator () (const Event &lhs, const Event &rhs) {
		if (lhs.first != rhs.first)
			return lhs.first < rhs.first;
		else
			return lhs.second < rhs.second;
	}
};

int main() 
{
	int n, k;
	scanf("%d %d", &n, &k);

	vector<Event> events(2 * n);
	int left, right;
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &left, &right);
		events[2 * i].first = left;
		events[2 * i].second = OPENING;
		events[2 * i + 1].first = right;
		events[2 * i + 1].second = CLOSING;
	}
	sort(events.begin(), events.end(), EventSorter());

	vector<pair<int, int> > segments;
	int balance, previous;
	balance = previous = 0;
	for (Event event : events) {
		balance -= event.second;
		if (previous == k - 1 && balance == k) {
			left = event.first;
		}

		if (previous == k && balance == k - 1) {
			right = event.first;
			segments.push_back(make_pair(left, right));
		}

		previous = balance;
	}

	printf("%d\n", segments.size());
	for (auto segment : segments) {
		printf("%d %d\n", segment.first, segment.second);
	}
}
