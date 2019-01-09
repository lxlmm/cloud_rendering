/**
 *    > Author:   UncP
 *    > Mail:     770778010@qq.com
 *    > Github:   https://www.github.com/UncP/Giraffe
 *    > Description:
 *
 *    > Created Time: 2016-10-29 22:59:17
**/

#include "mirror.hpp"

namespace Giraffe {

Color Mirror::sample(const Vector3d &out, Vector3d &in, const Vector3d &normal, double &pdf)
{
	in = normalize(out - (2 * dot(out, normal)) * normal);

	return color_;
}

} // namespace Giraffe
