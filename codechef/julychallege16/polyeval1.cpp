#include <iostream>
#include <functional>
#include <memory>
#include <vector>
#include <cstdint>
#include <unordered_map>

const uint32_t MAX = 250000;
const uint32_t MOD = 786433;

struct Polynomial {
	Polynomial(uint32_t degree)
		: degree_(degree), a_(degree + 1) {} 

	Polynomial(const Polynomial& other) = default;

	uint32_t degree_;
	std::vector<uint32_t> a_;
};

Polynomial* Multiply(const Polynomial& lhs, const Polynomial& rhs) {
	Polynomial* res = new Polynomial(rhs.degree_ + lhs.degree_);
	for (uint32_t i = 0; i <= rhs.degree_; ++i) {
		for (uint32_t j = 0; j <= lhs.degree_; ++j) {
			uint32_t temp = (rhs.a_[i] * lhs.a_[j]) % MOD;
			res->a_[i + j] = (res->a_[i + j] + temp) % MOD;
		}
	}
	return res;
}

Polynomial* GetMod(const Polynomial& lhs, const Polynomial& rhs) {
	if (lhs.degree_ < rhs.degree_) {
		return new Polynomial(lhs);
	}
	uint32_t delta = lhs.degree_ - rhs.degree_;
	std::vector<uint32_t> coeff;
	for (int i = 0; i < delta; ++i) {
		coeff.push_back(lhs.a_[i] * (*rhs.a_.rbegin()) % MOD);
	}
	for (int i = delta; i <= lhs.degree_; ++i) {
		uint32_t temp = (lhs.a_[i] * (*rhs.a_.rbegin()) + MOD - 
				rhs.a_[i - delta] * (*lhs.a_.rbegin())) % MOD;
		coeff.push_back(temp);
	}
	while (!coeff.empty() && *coeff.rbegin() == 0) {
		coeff.pop_back();
	}
	if (coeff.empty()) {
		return new Polynomial(0);
	}
	Polynomial temp(coeff.size() - 1);
	for (int i = 0; i <= temp.degree_; ++i) {
		temp.a_[i] = coeff[i];
	}
	return GetMod(temp, rhs);
}

using Range = std::pair<uint32_t, uint32_t>;
namespace std {
	template<>
	struct hash<Range> {
		size_t operator()(const Range& key) const {
			return std::hash<uint32_t>()(key.first) ^
					(std::hash<uint32_t>()(key.second) >> 1);
		}
	};
}

void Prepare(std::unordered_map<Range, 
				std::unique_ptr<Polynomial> >& cache, 
				uint32_t begin, uint32_t end, uint32_t u[]) {
	Range range = std::make_pair(begin, end);
	if (begin == end) {
		cache[range].reset(new Polynomial(1));
		cache[range]->a_[1] = MOD - u[begin];
		return;
	} 
	uint32_t mid = ((begin + end) >> 1);
	Prepare(cache, begin, mid, u);
	Prepare(cache, mid + 1, end, u);
	Range left = std::make_pair(begin, mid);
	Range right = std::make_pair(mid + 1, end);
	cache[range].reset(Multiply(*cache[left], *cache[right]));
}

void Analyze(const Polynomial& f, uint32_t begin, uint32_t end,
			uint32_t res[], uint32_t u[],
			std::unordered_map<Range, std::unique_ptr<Polynomial> >& cache) {
	if (f.degree_ == 1) {
		for (uint32_t i = begin; i <= end; ++i) {
			res[i] = (u[i] * f.a_[1] + f.a_[0]) % MOD;
		}
		return;
	}
	uint32_t mid = ((begin + end) >> 1);
	std::unique_ptr<Polynomial> left(GetMod(f, *cache[std::make_pair(begin, mid)]));
	std::unique_ptr<Polynomial> right(GetMod(f, *cache[std::make_pair(mid + 1, end)]));
	Analyze(*left, begin, mid, res, u, cache);
	Analyze(*right, mid + 1, end, res, u, cache);
}

int main() {
	std::ios_base::sync_with_stdio(0);
	uint32_t n, q;
	std::cin >> n;

	Polynomial f(n);
	
	for (uint32_t i = 0; i <= n; ++i) {
		std::cin >> f.a_[i];
	}

	std::cin >> q;
	uint32_t u[q], res[q];
	for (uint32_t i = 0; i < q; ++i) {
		std::cin >> u[i];
	}
	
	std::unordered_map<Range, std::unique_ptr<Polynomial> > cache;
	Prepare(cache, 0, q - 1, u);
	Analyze(f, 0, q - 1, res, u, cache);

	for (uint32_t i = 0; i < q; ++i) {
		std::cout << res[i] << std::endl;
	}


}





