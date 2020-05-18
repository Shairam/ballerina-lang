/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerinalang.compiler.syntax.tree;

import io.ballerinalang.compiler.internal.parser.tree.STNode;

import java.util.Objects;

/**
 * This is a generated syntax tree node.
 *
 * @since 2.0.0
 */
public class ExpressionFunctionBodyNode extends FunctionBodyNode {

    public ExpressionFunctionBodyNode(STNode internalNode, int position, NonTerminalNode parent) {
        super(internalNode, position, parent);
    }

    public Token rightDoubleArrow() {
        return childInBucket(0);
    }

    public ExpressionNode expression() {
        return childInBucket(1);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(NodeTransformer<T> visitor) {
        return visitor.transform(this);
    }

    @Override
    protected String[] childNames() {
        return new String[]{
                "rightDoubleArrow",
                "expression"};
    }

    public ExpressionFunctionBodyNode modify(
            Token rightDoubleArrow,
            ExpressionNode expression) {
        if (checkForReferenceEquality(
                rightDoubleArrow,
                expression)) {
            return this;
        }

        return NodeFactory.createExpressionFunctionBodyNode(
                rightDoubleArrow,
                expression);
    }

    public ExpressionFunctionBodyNodeModifier modify() {
        return new ExpressionFunctionBodyNodeModifier(this);
    }

    /**
     * This is a generated tree node modifier utility.
     *
     * @since 2.0.0
     */
    public static class ExpressionFunctionBodyNodeModifier {
        private final ExpressionFunctionBodyNode oldNode;
        private Token rightDoubleArrow;
        private ExpressionNode expression;

        public ExpressionFunctionBodyNodeModifier(ExpressionFunctionBodyNode oldNode) {
            this.oldNode = oldNode;
            this.rightDoubleArrow = oldNode.rightDoubleArrow();
            this.expression = oldNode.expression();
        }

        public ExpressionFunctionBodyNodeModifier withRightDoubleArrow(Token rightDoubleArrow) {
            Objects.requireNonNull(rightDoubleArrow, "rightDoubleArrow must not be null");
            this.rightDoubleArrow = rightDoubleArrow;
            return this;
        }

        public ExpressionFunctionBodyNodeModifier withExpression(ExpressionNode expression) {
            Objects.requireNonNull(expression, "expression must not be null");
            this.expression = expression;
            return this;
        }

        public ExpressionFunctionBodyNode apply() {
            return oldNode.modify(
                    rightDoubleArrow,
                    expression);
        }
    }
}
