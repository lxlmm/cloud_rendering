/**
 *    > Author:   UncP
 *    > Mail:     770778010@qq.com
 *    > Github:   https://www.github.com/UncP/Giraffe
 *    > Description:
 *
 *    > Created Time: 2016-10-29 23:00:05
**/

#ifndef _GLASS_MATERIAL_HPP_
#define _GLASS_MATERIAL_HPP_

#include "../core/material.hpp"
#include "../core/texture.hpp"

namespace Giraffe {

class Glass : public Material
{
	public:
		Glass(Type type):Material(type), color_(Vector3d(0.999)) {
			assert(type == kRefract);
		}

		Color sample(const Vector3d &out, Vector3d &in, const Vector3d &normal, double &pdf)override;

	private:
		using Material::type_;
		Vector3d color_;
};

} // namespace Giraffe

#endif /* _GLASS_MATERIAL_HPP_ */